#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <math.h>
//-lm pour compiler
#define Lambda 9
#define Mu 10

#define EPSILON 1e-5
#define MAXEVENT 1000000	//taille max de l'echeancier
#define MAXTEMPS 1000	//cond d'arret

double temps = 0;
long int n = 0;		//nb de clients dans la file a l'instant temps
int compteur = 0;	//cond d'arret 2
double cumule = 0;

typedef struct Event {
	int type; //0 pour arrive 1 pour sortie
	double date;
	int etat; //0 pour non traité 1 pour non traité
}event;

typedef struct Echeancier {
	event Tab[MAXEVENT];
	int taille;
}echeancier;

echeancier Ech;

double Exponnentielle(int lbda) {
	double r = (double)random()/RAND_MAX;	//entre 0 et 1
	
	while(r==0 || r==1) {	//tant que x vaut 0 ou 1 on refait un random
		r = (double)random()/RAND_MAX;
	}
	return -log(r)/(lbda*1.0); // - log(u)/lamda, avec U = unif(0,1)
}

void Ajouter_Ech(event e) {
	if(Ech.taille < MAXEVENT) {
		Ech.Tab[Ech.taille] = e;
		Ech.taille++;
		printf("Taille = %d\n", Ech.taille);
	}
	else (printf("echeancier PLEIN"));
}

void Init_Ech(){
	event e;
	e.type = 0;
	e.date = 0;
	e.etat = 0;
	Ech.taille = 0;
	Ajouter_Ech(e);
}

void Arrive_Event(event e) {
	printf("execution Arrivé Client\n");
	n++;
	
	event e1;
	e1.type = 0;
	e1.date = e.date + Exponnentielle(Lambda);
	e1.etat = 0;
	
	Ajouter_Ech(e1);
	
	if (n == 1) {
		event e2;
		e2.type = 1;
		e2.date = e.date + Exponnentielle(Mu);
		e2.etat = 0;
		
		Ajouter_Ech(e2);
	}
	
	temps = e.date;
	
}

void Service_Event(event e)	//service = Mu
{
	if (n>0) {
		n--;
		e.type = 1;
		e.date = e.date + Exponnentielle(Mu);
		e.etat = 0;
		
		Ajouter_Ech(e);
	}
	temps = e.date;
}

event Extraire() {
	int i,imin;
	event min;
	
	for (i = 0; i < Ech.taille; i++) {
		if(Ech.Tab[i].etat == 0) {
			min = Ech.Tab[i];
			imin = i;
			break;
		}
	}
	for (i = 0; i < Ech.taille; i++) {
		if(min.date > Ech.Tab[i].date && Ech.Tab[i].etat == 0) {
			min = Ech.Tab[i];
			imin = i;
		}
	}
	
	Ech.Tab[imin].etat = 1;
	return min;
}

void affiche_echeancier() {
	
	event e;
	printf (" temps %f et N = %ld taille : %d ", temps,n,Ech.taille);
	for (int i = 0; i < Ech.taille; i++)
	{
		e = Ech.Tab[i];
		
		if (e.type == 0) { printf (" arrive client %lf %d",e.date,e.etat);
		}
		if (e.type == 1) { printf ("FS %lf %d",e.date,e.etat);
		}
	}
		printf("\n \n");
}

int Condition_arret (long double Old, long double New) {
	if (fabs(Old-New) < EPSILON && temps > 1000)
	{
		compteur++;
		if (compteur > 1e3) {return 1;}
	}
	return 0;
}

void Simulateur(FILE *f1) {
	long double OldNmoyen;
	long double Nmoyen;
	Init_Ech();
	event e;
	//while (Condition_arret(OldNmoyen,Nmoyen) == 0) //temps < temps MAX // arret > 0
	while (temps < MAXTEMPS)
	{
		e = Extraire();
		cumule += (e.date-temps)*n;
		OldNmoyen = Nmoyen;
		Nmoyen = cumule/temps;
		if(temps == 0) {
			printf ("temps = 0 et N = 0 et Nmoyen = 0 \n");
			fprintf(f1,"0    0 \n");
		}
		else {
			printf("temps = %f et N = %ld et Nmoyen = %Lf\n",temps,n,Nmoyen);
			fprintf(f1,"%f     %Lf  \n",temps,Nmoyen);
		}
		
		if (e.type == 0) { Arrive_Event(e); }
		if (e.type == 1) { Service_Event(e); }
	}
}
int main () {
	FILE *f1 = fopen("Simulation_MM1.data","w");
	srandom(getpid() + time(NULL));
	Simulateur (f1);
	fclose(f1);
	exit(0);
}
	
