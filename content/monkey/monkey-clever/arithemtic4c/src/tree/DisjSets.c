void union(int a,int b){
   s[b]=a;
}

int find(int a){
   if(s[a]<0)
      return x;
   else
      return find(s[a]);
}
