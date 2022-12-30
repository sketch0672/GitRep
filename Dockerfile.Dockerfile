FROM gradle:7.4.0-jdk17

WORKDIR /GitRep

COPY /GitRep .

RUN gradle installDist

CMD ./GitRep