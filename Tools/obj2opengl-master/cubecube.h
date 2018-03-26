/*
created with obj2opengl.pl

source file    : ./cubecube.obj
vertices       : 8
faces          : 12
normals        : 6
texture coords : 0


// include generated arrays
#import "./cubecube.h"

// set input data to arrays
glVertexPointer(3, GL_FLOAT, 0, cubecubeVerts);
glNormalPointer(GL_FLOAT, 0, cubecubeNormals);

// draw data
glDrawArrays(GL_TRIANGLES, 0, cubecubeNumVerts);
*/

unsigned int cubecubeNumVerts = 36;

float cubecubeVerts [] = {
  // f 1//1 2//1 3//1 4//1
  0.499999812500094, -0.499999750000125, -0.499999875000062,
  0.499999812500094, -0.499999750000125, 0.499999625000187,
  -0.499999687500156, -0.499999750000125, 0.499999625000187,
  // f 1//1 2//1 3//1 4//1
  0.499999812500094, -0.499999750000125, -0.499999875000062,
  -0.499999687500156, -0.499999750000125, -0.499999875000062,
  -0.499999687500156, -0.499999750000125, 0.499999625000187,
  // f 5//2 8//2 7//2 6//2
  0.499999812500094, 0.499999750000125, -0.499999375000312,
  -0.499999687500156, 0.499999750000125, -0.499999875000062,
  -0.499999687500156, 0.499999750000125, 0.499999625000187,
  // f 5//2 8//2 7//2 6//2
  0.499999812500094, 0.499999750000125, -0.499999375000312,
  0.499999312500344, 0.499999750000125, 0.500000124999937,
  -0.499999687500156, 0.499999750000125, 0.499999625000187,
  // f 1//3 5//3 6//3 2//3
  0.499999812500094, -0.499999750000125, -0.499999875000062,
  0.499999812500094, 0.499999750000125, -0.499999375000312,
  0.499999312500344, 0.499999750000125, 0.500000124999937,
  // f 1//3 5//3 6//3 2//3
  0.499999812500094, -0.499999750000125, -0.499999875000062,
  0.499999812500094, -0.499999750000125, 0.499999625000187,
  0.499999312500344, 0.499999750000125, 0.500000124999937,
  // f 2//4 6//4 7//4 3//4
  0.499999812500094, -0.499999750000125, 0.499999625000187,
  0.499999312500344, 0.499999750000125, 0.500000124999937,
  -0.499999687500156, 0.499999750000125, 0.499999625000187,
  // f 2//4 6//4 7//4 3//4
  0.499999812500094, -0.499999750000125, 0.499999625000187,
  -0.499999687500156, -0.499999750000125, 0.499999625000187,
  -0.499999687500156, 0.499999750000125, 0.499999625000187,
  // f 3//5 7//5 8//5 4//5
  -0.499999687500156, -0.499999750000125, 0.499999625000187,
  -0.499999687500156, 0.499999750000125, 0.499999625000187,
  -0.499999687500156, 0.499999750000125, -0.499999875000062,
  // f 3//5 7//5 8//5 4//5
  -0.499999687500156, -0.499999750000125, 0.499999625000187,
  -0.499999687500156, -0.499999750000125, -0.499999875000062,
  -0.499999687500156, 0.499999750000125, -0.499999875000062,
  // f 5//6 1//6 4//6 8//6
  0.499999812500094, 0.499999750000125, -0.499999375000312,
  0.499999812500094, -0.499999750000125, -0.499999875000062,
  -0.499999687500156, -0.499999750000125, -0.499999875000062,
  // f 5//6 1//6 4//6 8//6
  0.499999812500094, 0.499999750000125, -0.499999375000312,
  -0.499999687500156, 0.499999750000125, -0.499999875000062,
  -0.499999687500156, -0.499999750000125, -0.499999875000062,
};

float cubecubeNormals [] = {
  // f 1//1 2//1 3//1 4//1
  0, -1, 0,
  0, -1, 0,
  0, -1, 0,
  // f 1//1 2//1 3//1 4//1
  0, -1, 0,
  0, -1, 0,
  0, -1, 0,
  // f 5//2 8//2 7//2 6//2
  0, 1, 0,
  0, 1, 0,
  0, 1, 0,
  // f 5//2 8//2 7//2 6//2
  0, 1, 0,
  0, 1, 0,
  0, 1, 0,
  // f 1//3 5//3 6//3 2//3
  1, 0, 0,
  1, 0, 0,
  1, 0, 0,
  // f 1//3 5//3 6//3 2//3
  1, 0, 0,
  1, 0, 0,
  1, 0, 0,
  // f 2//4 6//4 7//4 3//4
  0, 0, 1,
  0, 0, 1,
  0, 0, 1,
  // f 2//4 6//4 7//4 3//4
  0, 0, 1,
  0, 0, 1,
  0, 0, 1,
  // f 3//5 7//5 8//5 4//5
  -1, 0, 0,
  -1, 0, 0,
  -1, 0, 0,
  // f 3//5 7//5 8//5 4//5
  -1, 0, 0,
  -1, 0, 0,
  -1, 0, 0,
  // f 5//6 1//6 4//6 8//6
  0, 0, -1,
  0, 0, -1,
  0, 0, -1,
  // f 5//6 1//6 4//6 8//6
  0, 0, -1,
  0, 0, -1,
  0, 0, -1,
};
