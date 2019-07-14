#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define m 10

int Suite_LCG(int x)
{
	return (7*x+3)%m;
}

int main (int argc, char *argv[])
{
	int x,c;
	int x0 = 0;
	
	while (x0 < m)	//x0 doit etre dans l'intervalle [0,m-1]
	{
		x = Suite_LCG(x0);
		c = 1;
		printf("%d - %d - ",x0,x);
		
		while (x != x0)
		{
			x = Suite_LCG(x);
			printf("%d - ",x);
			c++;
		}
		
		printf("\n Periode du générateur pour x0 = %d est : T = %d",x0,c);
		x0++;
	}
	
	exit(0);
}
