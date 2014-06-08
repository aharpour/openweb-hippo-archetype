package ${package}.componentsinfo.catalog;

import generated.beans.CarouselBanner;

import org.hippoecm.hst.core.parameters.Color;
import org.hippoecm.hst.core.parameters.DocumentLink;
import org.hippoecm.hst.core.parameters.FieldGroup;
import org.hippoecm.hst.core.parameters.FieldGroupList;
import org.hippoecm.hst.core.parameters.Parameter;

@FieldGroupList({
		@FieldGroup(titleKey = "pickers.group.title", value = { CarouselInfo.FIELD_BANNER1, CarouselInfo.FIELD_BANNER2,
				CarouselInfo.FIELD_BANNER3, CarouselInfo.FIELD_BANNER4, CarouselInfo.FIELD_BANNER5,
				CarouselInfo.FIELD_BANNER6 }),
		@FieldGroup(titleKey = "mixin.group.title", value = { CarouselInfo.FIELD_USER_MIXIN }),
		@FieldGroup(titleKey = "visual.group.title", value = { CarouselInfo.FIELD_SHOW_CAPTION,
				CarouselInfo.FIELD_CAPTION_BACKGROUND_COLOR, CarouselInfo.FIELD_FONT_COLOR }) })
public interface CarouselInfo {

	public static final String FIELD_BANNER1 = "banner1";
	public static final String FIELD_BANNER2 = "banner2";
	public static final String FIELD_BANNER3 = "banner3";
	public static final String FIELD_BANNER4 = "banner4";
	public static final String FIELD_BANNER5 = "banner5";
	public static final String FIELD_BANNER6 = "banner6";
	public static final String FIELD_USER_MIXIN = "useMixin";
	public static final String FIELD_SHOW_CAPTION = "showCaption";
	public static final String FIELD_CAPTION_BACKGROUND_COLOR = "captionBackgroundColor";
	public static final String FIELD_FONT_COLOR = "fontColor";

	@Parameter(name = FIELD_BANNER1)
	@DocumentLink(docType = CarouselBanner.JCR_TYPE, allowCreation = true, docLocation = "components/carousel")
	public String getBanner1();

	@Parameter(name = FIELD_BANNER2)
	@DocumentLink(docType = CarouselBanner.JCR_TYPE, allowCreation = true, docLocation = "components/carousel")
	public String getBanner2();

	@Parameter(name = FIELD_BANNER3)
	@DocumentLink(docType = CarouselBanner.JCR_TYPE, allowCreation = true, docLocation = "components/carousel")
	public String getBanner3();

	@Parameter(name = FIELD_BANNER4)
	@DocumentLink(docType = CarouselBanner.JCR_TYPE, allowCreation = true, docLocation = "components/carousel")
	public String getBanner4();

	@Parameter(name = FIELD_BANNER5)
	@DocumentLink(docType = CarouselBanner.JCR_TYPE, allowCreation = true, docLocation = "components/carousel")
	public String getBanner5();

	@Parameter(name = FIELD_BANNER6)
	@DocumentLink(docType = CarouselBanner.JCR_TYPE, allowCreation = true, docLocation = "components/carousel")
	public String getBanner6();

	@Parameter(name = FIELD_USER_MIXIN, defaultValue = "off")
	public boolean getUseMixin();

	@Parameter(name = FIELD_SHOW_CAPTION, defaultValue = "on")
	public boolean getShowCaption();

	@Color
	@Parameter(name = FIELD_CAPTION_BACKGROUND_COLOR)
	public String getCaptionBackgroundColor();

	@Color
	@Parameter(name = FIELD_FONT_COLOR)
	public String getFontColor();

}
