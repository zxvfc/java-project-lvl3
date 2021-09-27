setup:
	gradle wrapper --gradle-version 7.2

clean:
	./gradlew clean

build:
	./gradlew build

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

install:
	./gradlew install

check-updates:
	./gradlew dependencyUpdates
