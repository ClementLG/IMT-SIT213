# Contexte

Projet scolaire de 2ème année d'école d'ingénieur (Ecole d'ingénieur IMT Atlantique - FIP - Brest).

Projet tranversale de POO avec Java et de telecoms (Mathématique et physique des signaux).

Projet réalisé en Septembre/octobre 2020.

Ouverture du projet en public le 14/02/2021.

Des script bash pour utiliser le projet sont présents

# Résumé du sujet

Creation d'un simulateur d'une chaine de transmission d'un emmeteur au recepteur.

![alt text](https://github.com/ClementLG/IMT-SIT213/blob/master/ChaineDeTransmission.PNG)

L’objectif consiste à transmettre un message d’un point d’entrée à un point de sortie,
via un canal de transmission (ou de communication). Le message d’entrée est émis par
une source d’entrée. Les messages considérés dans cet atelier seront des suites de
symboles binaires (0 ou 1) correspondant à des informations échantillonnées et
quantifiées sur deux niveaux logiques. Le message de sortie sera – autant que faire se peut -
semblable au message d’entrée. Ce dernier étant incapable de traverser le canal de
propagation tel quel, on l’adaptera aux caractéristiques physiques du canal en le
convertissant au moyen d’un transducteur en un « vecteur » adapté à la transmission,
appelé signal. Ce dernier sera injecté dans le canal au moyen d’un émetteur. À l’autre
extrémité du canal, il sera récupéré et traité par le récepteur et le transducteur de
réception.
Les principaux canaux de transmission rencontrés dans la nature sont : le canal Hertzien
(espace libre), le canal guidé électrique (câble), le canal guidé Optique (fibre), le canal
acoustique aérien et le canal acoustique sous-marin. Chaque canal de propagation devant
être utilisé à une fréquence bien particulière, le message est transposé autour de cette
fréquence par l’opération de modulation. En outre, le canal sera une source de bruit pour
les signaux qu’il transporte. Les principales sources de bruit rencontrées en pratique sont :
la dispersion de trajets, la dispersion chromatique, le bruit de détection (grenaille), le bruit
thermique et le bruit d’amplification.
Par la suite, chaque composant du système de transmission entre la source et la
destination sera dénommé transmetteur.


# Contact

:email: clement.le-gruiec@imt-atlantique.net

# Utilisation:

#### _-mess m_ 
précise le message ou la longueur du message à émettre :
- Si m est une suite de 0 et de 1 de longueur au moins égale à 7, m est le message à émettre.
- Si m comporte au plus 6 chiffres décimaux et correspond à la représentation en base 10 d'un entier, cet entier est la longueur du message que le simulateur doit générer et transmettre.
- Par défaut le simulateur doit générer et transmettre un message de longueur 100.

#### _-s_ 
indique l’utilisation des sondes. Par défaut le simulateur n’utilise pas de sondes

#### _-seed v_
précise l’utilisation d’une semence pour l’initialisation des générateurs aléatoires du simulateur.

v doit être une valeur entière. L’utilisation d’une semence permet de rejouer à l’identique une simulation (à la fois pour le message émis et le bruitage s’il est activé).

Par défaut le simulateur n’utilise pas de semence pour initialiser ses générateurs aléatoires.

#### _-form f_
utilisation d’une transmission analogique, f précise la forme d’onde.

Le paramètre f peut prendre les valeurs suivantes :
- NRZ forme d'onde rectangulaire
- NRZT forme d'onde trapézoïdale (temps de montée ou de descente à 1/3 du temps bit)
- RZ forme d'onde impulsionnelle (amplitude min sur le premier et dernier tiers du temps bit, impulsionnelle sur le tiers central avec un max au milieu du temps bit égal à l’amplitude max)

Par défaut le simulateur doit utiliser la forme d’onde RZ pour le signal analogique.

#### _-nbEch ne_
utilisation d’une transmission analogique, ne précise le nombre d’échantillons par bit.

_ne_ doit être une valeur entière positive.

Par défaut le simulateur doit utiliser 30 échantillons par bit.

#### _-ampl min max_
utilisation d’une transmission analogique, min et max précisent l’amplitude min et max du signal.

min et max doivent être des valeurs flottantes (avec min < max).

Par défaut le simulateur doit utiliser 0.0f comme min et 1.0f comme max.

#### _-ti dt ar_
utilisation d’une transmission analogique multitrajet (‘trajet indirect’).

_dt_ précise le décalage temporel (en nombre d’échantillons) entre le trajet indirect du signal et le trajet direct, _ar_ précise l’amplitude relative du signal du trajet indirect par rapport à celle du signal du trajet direct.

Les _dt_ et _ar_ doivent être respectivement une valeur entière et une valeur flottante.

Plusieurs couples de valeurs (5 au maximum) peuvent être mis après le ‘-ti’ pour simuler autant de trajets indirects.

Par défaut le simulateur ne simule pas de trajets indirects, ce qui correspond à des valeurs 0 et 0.0f pour tous les trajets indirects

#### _-codeur_
précise l’utilisation d’un codeur (en émission) et d’un décodeur (en réception).

Par défaut le simulateur n’utilise pas de codage de canal


Exemple
Simulateur -s -mess 10         #Génère un signal aléatoire sur 10bits et l'affiche à l'écran
Simulateur -s -mess 10101010   #Traite le message 10101010 dans la chaine de transmission et l'affiche à l'écran
Simulateur -s                  #Affiche un message aléatoire de longueur 100
Simulateur -s -mess 10 -seed 40 -form NRTZ -ampl -5 5 #Genere un signal aleatoire (en fct de la seed 40) sur 10bits, le transmets en analogique 
                                                       sous la forme NRZT avec des amplitudes entre -5 et 5

# Fichiers contenus dans l'archive

- ./bin/
- ./cleanAll
- ./compile
- ./docs/
- ./erreurs.log
- ./genDoc
- ./lib/
- ./lib/org.hamcrest.core_1.3.0.v20180420-1519.jar
- ./lib/junit.jar
- ./README.md
- ./README.txt
- ./runTests
- ./simulateur
- ./src/
- ./src/ArgumentsException.java
- ./src/destinations/
- ./src/destinations/Destination.java
- ./src/destinations/DestinationFinale.java
- ./src/destinations/DestinationInterface.java
- ./src/ExportTEB.java
- ./src/information/
- ./src/information/Information.java
- ./src/information/InformationNonConforme.java
- ./src/Simulateur.java
- ./src/sources/
- ./src/sources/Source.java
- ./src/sources/SourceAleatoire.java
- ./src/sources/SourceFixe.java
- ./src/sources/SourceInterface.java
- ./src/transmetteurs/
- ./src/transmetteurs/CodageEmission.java
- ./src/transmetteurs/DecodageReception.java
- ./src/transmetteurs/Emetteur.java
- ./src/transmetteurs/Recepteur.java
- ./src/transmetteurs/Transmetteur.java
- ./src/transmetteurs/TransmetteurAnalogiqueBruite.java
- ./src/transmetteurs/TransmetteurAnalogiqueBruitReel.java
- ./src/transmetteurs/TransmetteurAnalogiqueParfait.java
- ./src/transmetteurs/TransmetteurParfait.java
- ./src/visualisations/
- ./src/visualisations/Sonde.java
- ./src/visualisations/SondeAnalogique.java
- ./src/visualisations/SondeLogique.java
- ./src/visualisations/SondePuissance.java
- ./src/visualisations/SondeTextuelle.java
- ./src/visualisations/Vue.java
- ./src/visualisations/VueCourbe.java
- ./src/visualisations/VueValeur.java
- ./tests/
- ./tests/AllTests.java
- ./tests/information/
- ./tests/information/InformationTest.java
- ./tests/SimulateurTest.java
- ./tests/sources/
- ./tests/sources/SourceAleatoireTest.java
- ./tests/sources/SourceFixeTest.java
- ./tests/transmetteurs/
- ./tests/transmetteurs/CodageEmissionTest.java
- ./tests/transmetteurs/DecodageReceptionTest.java
- ./tests/transmetteurs/EmetteurTest.java
- ./tests/transmetteurs/RecepteurTest.java
- ./tests/transmetteurs/TransmetteurAnalogiqueBruiteTest.java
- ./tests/transmetteurs/TransmetteurAnalogiqueBruitReelTest.java
- ./tests/transmetteurs/TransmetteurAnalogiqueParfaitTest.java
- ./tests/transmetteurs/TransmetteurParfaitTest.java
