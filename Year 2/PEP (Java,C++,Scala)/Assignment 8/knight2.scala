// Part 2 about finding a single tour for a board
//================================================

// copy any function you need from file knight1.scala

object CW7b {

type Pos = (Int, Int)    // a position on a chessboard 
type Path = List[Pos]    // a path...a list of positions

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

def legal_moves(dim: Int, path: Path, x: Pos) : List[Pos] = {
	val possibleMoves = List((x._1+1,x._2+2),(x._1+2,x._2+1),(x._1+2,x._2-1),(x._1+1,x._2-2),(x._1-1,x._2-2),(x._1-2,x._2-1),(x._1-2,x._2+1),(x._1-1,x._2+2))
	for (i<-possibleMoves;if (is_legal(dim,path)(i))) yield i  
}

//(2a) Implement a first-function that finds the first 
//     element, say x, in the list xs where f is not None. 
//     In that case Return f(x), otherwise None. If possible,
//     calculate f(x) only once.

def first(xs: List[Pos], f: Pos => Option[Path]) : Option[Path] = {
	if (xs.isEmpty) None
	else{
		val x = f(xs.head)
		if (x != None) x
		else{
			first(xs.tail,f)
		}
	}
}

//(2b) Implement a function that uses the first-function for
//     trying out onward moves, and searches recursively for a
//     knight tour on a dim * dim-board.

def first_tour(dim: Int, path: Path) : Option[Path] = {
	if (path.length == dim*dim) Option(path)
	else{
		first(legal_moves(dim,path,path.head),i => first_tour(dim, i::path)) 
	}
}



}
