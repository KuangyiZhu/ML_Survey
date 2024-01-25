写模式：  
pthread_mutex_lock(&w_mutex);  
写写写……  
pthread_mutex_unlock(&w_mutex);  
  
  
读模式：  
pthread_mutex_lock(&r_mutex);           
  
if(readers == 0)  
     pthread_mutex_lock(&w_mutex);  
readers++;  
pthread_mutex_unlock(&r_mutex);   
读读读……  
pthread_mutex_lock(&r_mutex);  
readers- -;  
if(reader == 0)  
     pthread_mutex_unlock(&w_mutex);  
pthread_mutex_unlock(&r_mutex);   
