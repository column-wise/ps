#include<stdio.h>
int main()
{
        int up,down,height;
        scanf("%d%d%d",&up,&down,&height);
        if(down < 1 || up <= down || height < up || height > 1000000000)
        {
                return -1;
        }

        if((height-up)%(up-down)==0)
        {
                printf("%d",(height-up)/(up-down) + 1);
        }
        else
        {
                printf("%d",(height-up)/(up-down) + 2);
        }
}
