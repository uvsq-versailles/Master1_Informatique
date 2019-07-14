Liste des commandes show
• La commande show running-config (ou show run) est probablement la plus utile pour
déterminer l’état en cours d’un routeur, car elle affiche la configuration courante qui est stockée dans un
fichier activé en mémoire RAM.
• La commande show startup-config (ou show start) affiche le contenu du fichier de la
configuration de sauvegarde enregistré dans la mémoire non volatile ou NVRAM. Ce fichier permet de
configurer le routeur lors du démarrage ou du redémarrage de celui-ci à l’aide de la commande reload. Il
contient le paramétrage détaillé des interfaces du routeur.
• show flash est utilisée pour afficher la mémoire flash disponible et la quantité utilisée. La
mémoire Flash contient la plate-forme logicielle Cisco IOS (Internetwork Operating System), aussi
appelée image.
• show arp affiche la table arp du routeur (ou le cache arp) qui met en correspondance les
adresses IP et les adresses MAC pour tous les hôtes connectés aux interfaces du routeur. Le mappage
des adresses IP avec les adresses MAC de chaque interface est inclus dans la table arp.
• show interface brief affiche l’état de toutes les interfaces configurées sur le routeur.
Faites la correspondance entre les interfaces de votre routeur et les informations données par la
commande show interface brief.
• La commande show protocol affiche l'état général des protocoles de couche 3 configurés (IP,
IPX, etc.), ainsi qu'un état de ces protocoles pour chaque interface.