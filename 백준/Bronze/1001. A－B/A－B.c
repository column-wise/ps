#include<stdio.h>

int main()
{

int a,b;
scanf("%d %d",&a,&b);

if(a<0 || 10<a || b<0 || 10<b)
return -1;

else
printf("%d\n",a-b);

}