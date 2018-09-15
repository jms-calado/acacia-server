FROM debian:stretch as builder

ADD . /tmp/ontology

RUN apt-get update \
    && apt-get install -y openjdk-8-jdk maven \
    && cd /tmp/ontology \
    && cp dev.yml prod.yml \
    && mvn package

FROM debian:stretch

COPY --from=builder /tmp/ontology/target/ontology-*.deb /tmp/ontology.deb

RUN apt-get update \
    && apt-get install -y openjdk-8-jdk \
    && dpkg -i /tmp/ontology.deb

ENTRYPOINT [ "/bin/bash", "-e", "/usr/bin/ontology" ]
