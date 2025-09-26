package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * TASK: This is your 3rd task.
 * Please implement the class to satisfy the interface. *
 */
public class YourSolver implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {

        Result result = findMaxSumAndPaths(pyramid.getData(), pyramid.getRows() - 1, 0, 0); //new ArrayList<>());
        System.out.println("Maximum sum: " + result.maxSum);
       // System.out.println("Paths with maximum sum:");
       // for (List<Integer> path : result.paths) {
       //     System.out.println(path);
       // }
        return result.maxSum;
    }
    // Recursive function to find the maximum sum and track paths dynamically (working upwards)
    public static Result findMaxSumAndPaths(int[][] pyramid, int row, int col, int currentSum){ //List<Integer> currentPath) {
        // Add the current element to the path
        //currentPath.add(pyramid[row][col]);
        currentSum += pyramid[row][col];

        // Base case: If we've reached the first row (bottom of the pyramid in reversed form)
        if (row == 0) {
            //List<List<Integer>> resultPaths = new ArrayList<>();
            //resultPaths.add(new ArrayList<>(currentPath));
            return new Result(currentSum); //resultPaths);
        }

        // Recursive case: Move to the row above (left or right)
        int upperRow = row - 1;  // Move upwards to the row above
        int leftCol = col;
        int rightCol = col + 1;

        Result leftResult = null;
        Result rightResult = null;

        // Check if left index is valid
        if (leftCol < pyramid[upperRow].length) {
            leftResult = findMaxSumAndPaths(pyramid, upperRow, leftCol, currentSum); //new ArrayList<>(currentPath));
        }

        // Check if right index is valid
        if (rightCol < pyramid[upperRow].length) {
            rightResult = findMaxSumAndPaths(pyramid, upperRow, rightCol, currentSum); //new ArrayList<>(currentPath));
        }

        // Compare the results from the left and right subtrees
        if (leftResult != null && rightResult != null) {
            if (leftResult.maxSum > rightResult.maxSum) {
                return leftResult;  // Left path has a higher sum
            } else if (leftResult.maxSum < rightResult.maxSum) {
                return rightResult;  // Right path has a higher sum
            } else {
                // If both paths have the same sum, merge the paths
                //List<List<Integer>> mergedPaths = new ArrayList<>(leftResult.paths);
                //mergedPaths.addAll(rightResult.paths);
                return new Result(leftResult.maxSum); //mergedPaths);
            }
        } else if (leftResult != null) {
            return leftResult;  // Only left path exists
        } else {
            return rightResult;  // Only right path exists
        }
    }
    
}
class Result {
    int maxSum;
   // List<List<Integer>> paths;

    Result(int maxSum){ //List<List<Integer>> paths) {
        this.maxSum = maxSum;
        //this.paths = paths;
    }
}


