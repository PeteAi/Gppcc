package AI;

import processing.core.PVector;

import static java.lang.Math.PI;

public class Brain {
    PVector[] directions;//series of vectors which get the dot to the goal (hopefully)
    int step = 0;


    Brain(int size) {
        directions = new PVector[size];
        randomize();
    }

    //--------------------------------------------------------------------------------------------------------------------------------
    //sets all the vectors in directions to a random vector with length 1
    void randomize() {
        for (int i = 0; i < directions.length; i++) {
            float randomAngle = (float) (Math.random() * 2 * PI);
            directions[i] = PVector.fromAngle(randomAngle);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    //returns a perfect copy of this brain object
    Brain cloneBrain() {
        Brain clone = new Brain(directions.length);
        for (int i = 0; i < directions.length; i++) {
            clone.directions[i] = directions[i].copy();
        }

        return clone;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------

    //mutates the brain by setting some of the directions to random vectors
    void mutate() {
        float mutationRate = 0.01f;//chance that any vector in directions gets changed
        for (int i = 0; i < directions.length; i++) {
            float rand = (float) Math.random();
            if (rand < mutationRate) {
                //set this direction as a random direction
                float randomAngle = (float) (Math.random() * 2 * PI);
                directions[i] = PVector.fromAngle(randomAngle);
            }
        }
    }
}