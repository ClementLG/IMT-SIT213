#
# LE GRUIEC Clément
# LE DUC Elouan
# MAQUIN Philippe
# FRAIGNAC Guillaume
# LE JEUNE Matthieu
# ETAPE/TP 4 - SIT213
# 09-2020
#

Etape 4 du projet simulateur.

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
src/transmetteurs/Emetteur.java
src/transmetteurs/Recepteur.java
src/transmetteurs/Transmetteur.java
src/transmetteurs/TransmetteurParfait.java
src/transmetteurs/TransmetteurParfaitAnalogique.java
src/transmetteurs/TransmetteurAnalogiqueBruite.java
src/transmetteurs/TransmetteurAnalogiqueBruitReel.java
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

-seed v
précise l’utilisation d’une semence pour l’initialisation des générateurs aléatoires du simulateur.
v doit être une valeur entière. L’utilisation d’une semence permet de rejouer à l’identique une simulation
(à la fois pour le message émis et le bruitage s’il est activé).
Par défaut le simulateur n’utilise pas de semence pour initialiser ses générateurs aléatoires.

-form f
utilisation d’une transmission analogique, f précise la forme d’onde.
Le paramètre f peut prendre les valeurs suivantes :
NRZ forme d'onde rectangulaire
NRZT forme d'onde trapézoïdale (temps de montée ou de descente à 1/3 du temps bit)
RZ forme d'onde impulsionnelle (amplitude min sur le premier et dernier tiers du temps bit,
impulsionnelle sur le tiers central avec un max au milieu du temps bit égal à l’amplitude max)
Par défaut le simulateur doit utiliser la forme d’onde RZ pour le signal analogique.

-nbEch ne
utilisation d’une transmission analogique, ne précise le nombre d’échantillons par bit.
ne doit être une valeur entière positive.
Par défaut le simulateur doit utiliser 30 échantillons par bit.

-ampl min max
utilisation d’une transmission analogique, min et max précisent l’amplitude min et max du signal.
min et max doivent être des valeurs flottantes (avec min < max).
Par défaut le simulateur doit utiliser 0.0f comme min et 1.0f comme max.

-ti dt ar
utilisation d’une transmission analogique multitrajet (‘trajet indirect’)
dt précise le décalage temporel (en nombre d’échantillons) entre le trajet indirect du signal et le trajet
direct,
ar précise l’amplitude relative du signal du trajet indirect par rapport à celle du signal du trajet direct.
Les dt et ar doivent être respectivement une valeur entière et une valeur flottante.
Plusieurs couples de valeurs (5 au maximum) peuvent être mis après le ‘-ti’ pour simuler
autant de trajets indirects.
Par défaut le simulateur ne simule pas de trajets indirects, ce qui correspond à des valeurs 0 et 0.0f
pour tous les trajets indirects



Exemple
Simulateur -s -mess 10         #Génère un signal aléatoire sur 10bits et l'affiche à l'écran
Simulateur -s -mess 10101010   #Traite le message 10101010 dans la chaine de transmission et l'affiche à l'écran
Simulateur -s                  #Affiche un message aléatoire de longueur 100
Simulateur -s -mess 10 -seed 40 -form NRTZ -ampl -5 5 #Genere un signal aleatoire (en fct de la seed 40) sur 10bits, le transmets en analogique 
                                                       sous la forme NRZT avec des amplitudes entre -5 et 5
