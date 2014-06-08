/**
 * Copyright 2010-2013 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * modified by Smile Benelux (http://smile-benelux.com/)
 */
#set( $symbol_dollar = '$' )
package ${package}.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpClientFeedFetcher;
import com.sun.syndication.fetcher.impl.SyndFeedInfo;
import com.sun.syndication.io.FeedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedFetcher {

    private static final int MILLIS_PER_MINUTE = 60 * 1000;
    private static final Logger log = LoggerFactory.getLogger(FeedFetcher.class);

    private final FeedFetcherCache feedInfoCache;
    private final HashMap<String, Long> lastUpdateMap;

    public FeedFetcher() {
        feedInfoCache = HashMapFeedInfoCache.getInstance();
        lastUpdateMap = new HashMap<String, Long>();
    }
    
    /**
     * Only fetch a new feed after at least <updateInterval> seconds have passed since the last call.
     */
    public SyndFeed retrieveFeed(String feedUrl, int updateInterval, int connectTimeout, int readTimeout) {
        URL url = null;
        SyndFeed updatedFeed = null;
        
        try {
            url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            log.warn("Malformed feed url: '" + feedUrl + "'");
        }

        if (url != null && shouldUpdateFeed(feedUrl, updateInterval)) {
            updatedFeed = updateFeed(url, connectTimeout, readTimeout);
        }
        
        if (url != null && updatedFeed == null) {
        	// return cached feed
        	SyndFeedInfo feedInfo = feedInfoCache.getFeedInfo(url);
        	if (feedInfo != null) {
        		updatedFeed = feedInfo.getSyndFeed();
        	}
        }
        return updatedFeed;
    }

    private boolean shouldUpdateFeed(final String feedUrl, final int updateInterval) {
        int updateIntervalMillis = updateInterval * MILLIS_PER_MINUTE;
        long now = System.currentTimeMillis();
        synchronized(lastUpdateMap) {
            long lastUpdate = 0;
            if (lastUpdateMap.containsKey(feedUrl)) {
                lastUpdate = lastUpdateMap.get(feedUrl);
            }
            if (now > (lastUpdate + updateIntervalMillis)) {
                lastUpdateMap.put(feedUrl, now);
                return true;
            }
        }
        return false;
    }

    private SyndFeed updateFeed(URL url, int connectTimeout, int readTimeout) {
        HttpClientFeedFetcher feedFetcher = new HttpClientFeedFetcher(feedInfoCache);
        feedFetcher.setConnectTimeout(connectTimeout);
        feedFetcher.setReadTimeout(readTimeout);

        try {
            log.info("Retrieving feed '{}", url);
            return feedFetcher.retrieveFeed(url);
        } catch (IOException e) {
            log.warn("I/O problem while fetching feed '" + url + "': " + e.getMessage());
        } catch (FeedException e) {
            log.warn("Failed to parse feed '" + url + "': " + e.getMessage());
        } catch (FetcherException e) {
            log.warn("Error " + e.getResponseCode() + " while fetching feed '" + url + "'");
        }

        return null;
    }   
}
