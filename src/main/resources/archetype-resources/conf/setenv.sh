REP_OPTS="-Drepo.upgrade=false -Drepo.config=file:${CATALINA_BASE}/conf/repository.xml -Drepo.path=${CATALINA_BASE}/repository"
L4J_OPTS="-Dlog4j.configuration=file:${CATALINA_BASE}/conf/log4j.xml"
JVM_OPTS="-server -Xmx2g -Xms512m -XX:PermSize=128m -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/ -XX:OnOutOfMemoryError=${CATALINA_BASE}/conf/tomcat-restart-email.sh"

CATALINA_OPTS="${JVM_OPTS} ${REP_OPTS} ${L4J_OPTS}"