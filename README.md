# Abelian Sandpile Simulation

This project is a parallel implementation of an Abelian Sandpile simulation using Java. The goal is to optimize the performance of a cellular automaton model by leveraging the Fork/Join framework for parallel computation.

## Table of Contents
- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Performance](#performance)

## Introduction

The Abelian Sandpile model is a type of cellular automaton that simulates a grid of cells, each containing a certain number of "grains of sand." When a cell exceeds a threshold, it "topples," distributing grains to neighboring cells. This simulation explores the emergent patterns that arise from simple rules, with a focus on parallelizing the computation to improve performance.

## Project Structure


    .
    ├── src/
    │   ├── serialAbelianSandpile/
    │   │   ├── AutomatonSimulation.java
    │   │   └── Grid.java
    │   ├── parallelAbelianSandpile/
    │   │   ├── ParallelAutomatonSimulation.java
    │   │   └── ParallelGrid.java
    ├── input/
    │   ├── example_input_1.csv
    │   └── example_input_2.csv
    ├── output/
    │   ├── example_output_1.png
    │   └── example_output_2.png
    ├── Makefile
    └── README.md
    └── Report.pdf





- `src/`: Contains the Java source files for both serial and parallel implementations.
- `input/`: Contains sample input files in CSV format.
- `output/`: Stores the resulting PNG images from the simulation.
- `Makefile`: Script to compile and run the project.
- `README.md`: Project documentation.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Make (for running the Makefile)
- Git (for version control)

### Installation

1. Clone the repository:

   ```bash
   git clone git@github.com:ororisengpaadi/AbelianSandPileSimulation.git
   cd AbelianSandPileSimulation

2. Compile the project using the Makefile:

    ```bash
    make

## Usage 

2. Compile the project using the Makefile:

    ```bash
    make run ARGS="input/65_by_65.csv output/65_by_65.png"

this command runs the simulation with the specificied input file and outputs the final stable state as a PNG image.

## Performance 

The parallel implementation aims to achieve better performance compared to the serial version by dividing the grid into non-overlapping regions, processed concurrently using Java's Fork/Join framework. the project includes benchmarks comparing the speedup across different grid sizes and hardware configurations. 

See Report.pdf for more details 