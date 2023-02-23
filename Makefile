# COMMANDES #
JAVAC = javac

JAVA = java
EXEC_JAR = ${JAVA} -jar
JAVAC_OPTIONS = -encoding UTF8 -d build -cp build -implicit:none -sourcepath src
SRC = src/fr/projet/pong
BUILD = build/fr/projet/pong

# CHOIX NOMS
JAR_MP = pong.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #
${JAR_MP} :  build ${BUILD}/Main.class
	jar -cfe ${JAR_MP} fr.projet.pong.Main -C build fr

run : ${JAR_MP}
	${EXEC_JAR} ${JAR_MP}

build : 
	@mkdir build

clean :
	rm -rf ${BUILD}/*

cleanjar :
	rm *.jar

${BUILD}/Main.class :	${SRC}/Main.java \
					  	${BUILD}/Game.class \
						${BUILD}/KeysListener.class \
						${BUILD}/Constants.class
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/Game.class : 	${SRC}/Game.java\
						${BUILD}/Player.class \
						${BUILD}/Ball.class
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/KeysListener.class : ${SRC}/KeysListener.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/Player.class : ${SRC}/Player.java \
						${BUILD}/Score.class 
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/Ball.class : ${SRC}/Ball.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/Constants.class : ${SRC}/Constants.java
	${JAVAC} ${JAVAC_OPTIONS} $<

${BUILD}/Score.class : ${SRC}/Score.java
	${JAVAC} ${JAVAC_OPTIONS} $<