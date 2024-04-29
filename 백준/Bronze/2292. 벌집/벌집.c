#include<stdio.h>
int main(){
        int a = 0, i = 1;
        scanf("%d",&a);
        a--;
        while(a>0)
        {
                a= a-6*i;
                i++;
        }
        printf("%d",i);
}
