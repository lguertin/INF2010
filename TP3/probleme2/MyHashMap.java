package probleme2;


public class MyHashMap<KeyType, ValueType>
{
   private DoubleHashingTable< Entry<KeyType, ValueType> > items;

   public MyHashMap( )
   {
      items = new DoubleHashingTable< Entry<KeyType,ValueType> >();
   }

   public void put(KeyType key, ValueType val)
   {
      items.insert(new Entry<KeyType,ValueType>(key,val));
   }

   public ValueType get(KeyType key)
   {
      return (items.get(new Entry<KeyType,ValueType>(key,null) )).value;
   }

   public boolean isEmpty()
   {
      return ( items.nbElement() == 0 ); 
   }

   public int nbreOccurence(ValueType x) {
	   int nbreOcc = 0;
	   for(int i = 0; i < items.nbElement(); i++) {
		   if(items.getElementFromKey(i) != null && items.getElementFromKey(i).value == x)
			   nbreOcc++;
	   }
	   return nbreOcc;
   }
   
   private static class Entry<KeyType,ValueType>
   {
      public KeyType key;
      public ValueType value;

      public Entry(KeyType key, ValueType value)
      {
         this.key = key;
         this.value = value;
      }

      public boolean equals(Object cmp)
      {
         return this.hashCode() == cmp.hashCode();
      }

      public int hashCode()
      {
         return key.hashCode();
      }
   }    
}