public static String add(String a,String b) {
                int index1=a.indexOf('.');
                int index2=b.indexOf('.');
                int aleft=a.length()-1-index1;
                int bleft=b.length()-1-index2;
                if(bleft>aleft) {
                        return add(b,a);
                }
                
                int astart=a.length()-1;
                int bstart=a.length()-1+index2-index1;

                StringBuilder sb=new StringBuilder();
                int carry=0;
                while(astart>=0 ||(bstart>=0 && bstart<b.length())||carry!=0) {
                        if(astart==index1 && bstart==index2) {
                           sb.append('.');        
                        } else {
                                
                                if(astart>=0) {
                                        carry +=a.charAt(astart)-'0';-baidu 1point3acres
                                }
                                if (bstart>=0 && bstart<b.length()) {
                                        carry +=b.charAt(bstart)-'0';
                                }
                                sb.append(carry%10);
                                carry/=10;
                        }
                        astart--;
                        bstart--;
                }
                return sb.reverse().toString();
        }
-------------------
  
public String add(String a,String b) {    
    int ia=a.length()-1;   
    int ib=b.length()-1;
    int carry=0;
    StringBuilder sb=new StringBuilder();
    while(ia>=0||ib>=0||carry!=0) {
         if(ia>=0) {
              carry+=a.charAt(ia--)-'0';
        }
         if(ib>=0) {
              carry+=b.charAt(ib--)-'0';
        }   
        sb.insert(0,carry%10);
        carry/=10;
    }
    return sb.toSrtring();
}
