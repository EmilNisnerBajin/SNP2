package v1.testSamples;

public class ProbabilityArrayGenerator {

	public static double[] getRandomProbabilityArray(int dimension) {
		if(dimension <= 0) {
			throw new IllegalArgumentException("Array dimension can't be zero or less!");
		}
		double [] array = new double[dimension];
		
		double randomValue;
		double sum = 0;
		for(int i=0; i<dimension; i++) {
			randomValue = Math.random() * 100;
			randomValue = Math.round(randomValue);
			if(randomValue == 0) {
				randomValue+=1;
			}
			System.out.println(randomValue);
			sum+= randomValue;
			array[i] = randomValue;
		}
		double news=0;
		for(int i=0; i<dimension; i++) {
			array[i] = array[i] / sum;
			news+=array[i];
		}
		System.out.println(news);
		return array;
	}
}
