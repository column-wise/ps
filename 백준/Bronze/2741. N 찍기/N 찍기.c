#include<stdio.h>
  
int main(){
        int n;

        scanf("%d",&n);

        if(n<1||n>100000)
        {
                return 0;
        }

        int i;
        for(i=1;i<=n;i++)
        {
                printf("%d\n",i);
        }
}
