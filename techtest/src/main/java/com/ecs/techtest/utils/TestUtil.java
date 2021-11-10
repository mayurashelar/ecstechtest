package com.ecs.techtest.utils;

import java.util.List;
import java.util.stream.Stream;

public class TestUtil {

	/**
	 * This method return the index of the array where the sum of integers at
	 * the index on the left is equal to the sum of integers on the right.
	 * @param list
	 * @param sliceIndex
	 * @return
	 */

	public static int calculateIndex(List<Integer> list, int sliceIndex) {
		int size = list.size();
		int frontSum = (getSliceOfStream(list.stream(), 0, sliceIndex).mapToInt(Integer::intValue).sum());
		int backSum = (getSliceOfStream(list.stream(), sliceIndex, size).mapToInt(Integer::intValue).sum());
		if (frontSum < backSum) {
			sliceIndex++;
			calculateIndex(list, sliceIndex);
		} else if (frontSum > backSum) {
			sliceIndex--;
			calculateIndex(list, sliceIndex);
		}

		return sliceIndex;

	}

	/**
	 * This method returns the slice of the stream from startIndex to endIndex
	 * @param stream
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public static Stream<Integer> getSliceOfStream(Stream<Integer> stream, int startIndex, int endIndex) {
		return stream
				// specify the number of elements to skip
				.skip(startIndex)

				// specify the no. of elements the stream
				// that should be limited
				.limit(endIndex - startIndex + 1);
	}

}
