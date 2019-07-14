
#define Lambda 1.0
#define TAILLEMAX 1e4

long double *Valeurs;
long double *FctRepart1;
long double *FctRepart2;

void Init() 
{
	srandom(getpid() + time(NULL));
	Valeurs = calloc(TAILLEMAX,sizeof(long double));
