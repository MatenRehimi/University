// Part 1 about finding and counting Knight's tours
//==================================================

object CW7a {

type Pos = (Int, Int)    // a position on a chessboard 
type Path = List[Pos]    // a path...a list of positions

//(1a) Complete the function that tests whether the position 
//     is inside the board and not yet element in the path.

def is_legal(dim: Int, path: Path)(x: Pos) : Boolean = {

	if (x._1 > dim-1 || x._1 < 0 || x._2 > dim-1 || x._2 < 0 ) {
		false
	}else{
		if (path.contains(x)) {
			false
		}else{
			true
		}
	}
}

//(1b) Complete the function that calculates for a position 
//     all legal onward moves that are not already in the path. 
//     The moves should be ordered in a "clockwise" manner.
 
def legal_moves(dim: Int, path: Path, x: Pos) : List[Pos] = {
	val possibleMoves = List((x._1+1,x._2+2),(x._1+2,x._2+1),(x._1+2,x._2-1),(x._1+1,x._2-2),(x._1-1,x._2-2),(x._1-2,x._2-1),(x._1-2,x._2+1),(x._1-1,x._2+2))
	for (i<-possibleMoves;if (is_legal(dim,path)(i))) yield i  
}


//some test cases
//
//assert(legal_moves(8, Nil, (2,2)) == 
//  List((3,4), (4,3), (4,1), (3,0), (1,0), (0,1), (0,3), (1,4)))
//assert(legal_moves(8, Nil, (7,7)) == List((6,5), (5,6)))
//assert(legal_moves(8, List((4,1), (1,0)), (2,2)) == 
//  List((3,4), (4,3), (3,0), (0,1), (0,3), (1,4)))
//assert(legal_moves(8, List((6,6)), (7,7)) == List((6,5), (5,6)))


//(1c) Complete the two recursive functions below. 
//     They exhaustively search for knight's tours starting from the 
//     given path. The first function counts all possible tours, 
//     and the second collects all tours in a list of paths.

def count_tours(dim: Int, path: Path) : Int = {

	if (path.length == dim*dim) 1
	else{
		(for (i<-legal_moves(dim,path,path(0))) yield count_tours(dim, i::path)).sum
	}
}

def enum_tours(dim: Int, path: Path) : List[Path] = {
	if (path.length == dim*dim) List(path)
	else{
		(for(i<-legal_moves(dim,path,path(0))) yield enum_tours(dim, i::path)).flatten
	}
}

}
