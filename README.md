# Contexte

Projet scolaire de 2ème année d'école d'ingénieur (IMT Atlantique).

Projet tranversale de POO avec Java et de telecoms (Mathématique et physique des signaux).

Projet réalisé en Septembre/octobre 2020.

Ouverture du projet en public le 14/02/2021.

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

