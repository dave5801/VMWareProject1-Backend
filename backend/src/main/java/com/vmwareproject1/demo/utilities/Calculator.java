package com.vmwareproject1.demo.utilities;

import com.vmwareproject1.demo.model;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private Map<CountyState, Double> scoreMap;

    /**
      The key into the map for getting the score
    */
    private static class CountyState {
        private String county;
        private String state;

        public CountyState(String county, String state) {
            this.county = county;
            this.state = state;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CountyState)) {
                return false;
            }
            CountyState rhs = (CountyState) o;
            return this.county.equals(rhs.county)
                && this.state.equals(rhs.state);
        }

        @Override
        public int hashCode() {
            return 37 * county.hashCode() + state.hashCode();
        }
    }
    
    /**
     * New creates a new Calculator object given a list of counties
     * @param counties the collection of county objects
     */
    public static Calculator New(Collection<County> counties) {
        double minTax = 0.0;
        double maxTax = 0.0;
        double minExports = 0.0;
        double maxExports = 0.0;
        int minPop = 0;
        int maxPop = 0;
        double minUnemployment = 0.0;
        double maxUnemployment = 0.0;
        double minIncome = 0.0;
        double maxIncome = 0.0;

        boolean bFirstTime = true;

        for (County county : counties) {
            if (bFirstTime) {
                bFirstTime = false;
                minTax = county.getTaxRate();
                maxTax = minTax;
                minExports = county.getExportDollarAmount();
                maxExports = minExports;
                minPop = county.getPopulation();
                maxPop = minPop;
                minUnemployment = county.getUnemployment();
                maxunemployment = minUnemployment;
                minIncome = county.getIncome();
                maxIncome = minIncome;
            } else {
                double tax = country.getTaxRate();
                if (tax < minTax) {
                    minTax = tax;
                } else if (tax > maxTax) {
                    maxTax = tax;
                }
                double exports = country.getExportDollarAmount();
                if (exports < minExports) {
                    minExports = exports;
                } else if (exports > maxExports) {
                    maxExports = exports;
                }
                int pop = country.getPopulation();
                if (pop < minPop) {
                    minPop = pop;
                } else if (pop > maxPop) {
                    maxPop = pop;
                }
                int pop = country.getPopulation();
                if (pop < minPop) {
                    minPop = pop;
                } else if (pop > maxPop) {
                    maxPop = pop;
                }
                double unemployment = country.getUnemployment();
                if (unemployment < minUnemployment) {
                    minUnemployment = unemployment;
                } else if (unemployment > maxUnemployment) {
                    maxUnemployment = unemployment;
                }
                double income = country.getIncome();
                if (income < minIncome) {
                    minIncome = income;
                } else if (income > maxIncome) {
                    maxIncome = income;
                }
            }
        }
        Map<CountyState, Double> scoreMap = new HashMap<CountyState, Double>();

        for (County county : counties) {
            double score = 0.0;
            
            // Compute tax rate score
            score += (20.0 - computeScore(20.0, minTax, maxTax, county.getTaxRate()));
            // Compute export score
            score += computeScore(20.0, minExports, maxExports, county.getExportDollarAmount());

            // Compute population score
            score += computeScore(20.0, (double) minPop, (double) maxPop, (double) county.getPopulation());

            // Compute unemployment score
            score += (20.0 - computeScore(20.0, minUnemployment, maxUnemployment, county.getUnemployment()));

            // Compute income sccore
            score += computeScore(20.0, minIncome, maxIncome, county.getIncome());
            CountyState countyState = new CountySate(county.getCounty(), county.getState());
            scoreMap.put(countyState, score);
        }
        return new Calculator(scoreMap);
    } 

    private Calculator(Map<CountyState, Double> scoreMap) {
        this.scoreMap = scoreMap
    }

    /**
     * getScore returns the score betwen 0.0 and 100.0 for the given county
     * and state. If the county and state pair given aren't recognized,
     * getScore return null.
     */
    public getScore(String county, String state) Double {
        return scoreMap.get(new CountyState(county, state))
    }

    private static computeScore(
        double weight, double min, double max, double actual) {
        // Special case where min and max are the same.
        if (min == max) {
            return weight;
        }
        return weight*(actual-min)/(max-min)
    
    }
}
