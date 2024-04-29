#include<stdio.h>
  
int main(){
        int a;
        int b;

        scanf("%d%d",&a,&b);

        if(a<1||a>10||b<1||b>10)
        {
                return 0;
        }

        printf("%d\n",a*b);
}
