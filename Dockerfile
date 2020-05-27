FROM openjdk:8-slim

VOLUME /tmp

ARG DEPENDENCY=dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-cp","app:app/lib/*","br.com.r4s.adm.AdministracaoApplication"]
