FROM ghcr.io/graalvm/graalvm-ce:latest AS BUILDER

RUN curl -O https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein && \
    chmod +x lein && \
    mv lein /usr/bin/lein && \
    lein upgrade

RUN gu install native-image

COPY . /build
WORKDIR /build
RUN lein uberjar
COPY target/*-standalone.jar app.jar
RUN native-image --report-unsupported-elements-at-runtime \
             --initialize-at-build-time \
             --no-server \
             -jar ./app.jar \
             -H:Name=output

FROM scratch
COPY --from=BUILDER /build/output /opt/output
CMD ["/opt/output"]
