/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */ 
 
 /**
  * <p>A pseudo-random number generator. 
  *  
  * @author Robert Laniewski <depthn@gmail.com>
  * @version 1.0 
  */
public class RandomNumberGenerator {
   
	/**
	 * The underlying (double) values. 
	 */
	private double x = 2.0d, y, z;
	
	/** 
	 * Log to the base 10 of this value indicates how many digits after the decimal point to discard.
	 */
	private static final long PRECISION = 100000; 
	
	private void obfuscate() {
		for (int k = 0; k < 10000; k++) next();		
	}

	public RandomNumberGenerator() {
		obfuscate();
	}	
	
	public RandomNumberGenerator(double seedValue) {
		x = seedValue*seedValue;
		obfuscate();
	}

	public RandomNumberGenerator(float seedValue) {
		x = seedValue*seedValue;
		obfuscate();
	}	
	
	public RandomNumberGenerator(int seedValue) {
		x = seedValue*seedValue;
		obfuscate();
	}	

	/**
	 * Generates a new seed value using the current system time. 
	 */
	public void randomize() {
		x = System.currentTimeMillis();
		obfuscate();
	}
	
	private void next() {
		x = Math.log(x); // natural logarithm (log to the base e)
		x = x*x;

		z = x*PRECISION;
		y = Math.floor(z);    	
	}

	/**
	 * TBD: Under development 
	 */
	public float nextFloat(float range) {
		next();		
		float r = (float) (z-y)*range;		
		return r;
	}		
		
	public long nextLong(long range) {
		next();		
		long r = (long) Math.floor((z-y)*range);		
		return r;
	}

	public int nextInt(int range) {
		next();		
		int r = (int) Math.floor((z-y)*range);		
		return r;
	}	

	public byte nextByte(byte range) {
		next();		
		byte r = (byte) Math.floor((z-y)*range);		
		return r;
	}	

	public char nextChar(char range) {
		next();		
		char r = (char) Math.floor((z-y)*range);		
		return r;
	}		
	
	/**
	 * Prints out:
	 * <ul>
	 * 	<li>A series of random int values.</li>
	 * 	<li>A distribution table showing the occurrence of each value in the range.</li>
	 * </ul>
	 * 
	 * @param length The length of the series. 
	 * @param range The range of the series from 0 to the value specified (not inclusive).
	 * @param printTable Optionally print the distribution table.
	 */
	public void randomSeriesTestInt(int length, int range, boolean printTable) {
		randomize();

		int[] freq = new int[range];

		for (int k = 0; k < length; k++) {  	
			int r = nextInt(range);    				
			freq[r]++;			
			System.out.print(r+", ");
		}    	

		System.out.println();

		if (printTable) {
			for (int i = 0; i < range; i++) {
				System.out.println(i+": "+freq[i]);
			}  
		}
	}

	/**
	* Prints out a series of random long values.
	* 
	* @param length The length of the series. 
	* @param range The range of the series from 0 to the value specified (not inclusive).
	*/
	public void randomSeriesTestLong(int length, long range) {
		randomize();
		for (int k = 0; k < length; k++) {  	
			long r = nextLong(range);    						
			System.out.print(r+", ");
		}   
		System.out.println();
	}    
    
	/**
	* Prints out a series of random float values.
	* 
	* @param length The length of the series. 
	* @param range The range of the series from 0 to the value specified (not inclusive).
	*/
	public void randomSeriesTestFloat(int length, float range) {
		randomize();
		for (int k = 0; k < length; k++) {  	
			float r = nextFloat(range);    						
			System.out.print(r+", ");
		}
		System.out.println();    	
	}     

	public static void main(String[] args) {
		RandomNumberGenerator p = new RandomNumberGenerator();

		// Print out a series of 1,000 float values from 0 to 3.4028235E38.
		p.randomSeriesTestFloat(1000, Float.MAX_VALUE);                

		// Print out a series of 1,000 float values from 0 to 999.
		p.randomSeriesTestFloat(1000, 1000);                

		// Print out a series of 1,000 long values from 0 to 0x7fffffffffffffff.
		p.randomSeriesTestLong(1000, Long.MAX_VALUE);                                              

		// Print out a series of 100,000 int values from 0 to 999.
		p.randomSeriesTestInt(100_000, 1000, false);
	}

}
