#
# LE GRUIEC Clément
# LE DUC Elouan
# DEMOULIN Benjamin
# ETAPE/TP 1 - SIT213
# 09-2020
#

Etape 1 du projet simulateur.

Projet réalisé dans un cadre scolaire en 2e année
Ecole d'ingénieur IMT Atlantique - FIP - Brest

#################################################


Fichiers contenus dans l'archive:

bin/
cleanAll
compile
docs/
erreurs.log
genDoc
readMe.txt
runTests
Simulateur
src/
src/visualisations/
src/visualisations/Sonde.java
src/visualisations/SondeAnalogique.java
src/visualisations/SondeLogique.java
src/visualisations/SondePuissance.java
src/visualisations/SondeTextuelle.java
src/visualisations/Vue.java
src/visualisations/VueCourbe.java
src/visualisations/VueValeur.java
src/transmetteurs/
src/transmetteurs/Transmetteur.java
src/transmetteurs/TransmetteurParfait.java
src/sources/
src/sources/Source.java
src/sources/SourceAleatoire.java
src/sources/SourceFixe.java
src/sources/SourceInterface.java
src/information/
src/information/Information.java
src/information/InformationNonConforme.java
src/destinations/
src/destinations/Destination.java
src/destinations/DestinationFinale.java
src/destinations/DestinationInterface.java
src/Simulateur.java
src/ArgumentsException.java



#################################################

Utilisation:

L’option -mess m précise le message ou la longueur du message à émettre :
• Si m est une suite de 0 et de 1 de longueur au moins égale à 7, m est le message à émettre.
• Si m comporte au plus 6 chiffres décimaux et correspond à la représentation en base 10 d'un entier, cet entier est la longueur du message que le simulateur doit générer et transmettre.
• Par défaut le simulateur doit générer et transmettre un message de longueur 100.
L’option -s indique l’utilisation des sondes. Par défaut le simulateur n’utilise pas de sondes

Exemple
Simulateur -s -mess 10   #Génère un signal aléatoire sur 10bits et l'affiche à l'écran
Simulateur -s -mess 10101010   #Traite le message 10101010 dans la chaine de transmission et l'affiche à l'écran
Simulateur -s  #Affiche un message aléatoire de longueur 100


