FROM gradle:7.4.0-jdk17

WORKDIR /run.bat

COPY /run.bat

RUN gradle installDist

CMD ./run.bat