// Part 3 about finding a single tour using the Warnsdorf Rule
//=============================================================

// copy any function you need from files knight1.scala and
// knight2.scala

object CW7c {

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

//(3a) Complete the function that calculates a list of onward
//     moves like in (1b) but orders them according to Warnsdorf’s 
//     rule. That means moves with the fewest legal onward moves 
//     should come first.

def ordered_moves(dim: Int, path: Path, x: Pos) : List[Pos] = {
	legal_moves(dim,path,x).sortBy(legal_moves(dim,path,_).length)
}


//(3b) Complete the function that searches for a single *closed* 
//     tour using the ordered moves function.

def first_closed_tour_heuristic(dim: Int, path: Path) : Option[Path] = {
	if (path.length == dim*dim && legal_moves(dim,List(path.head),path.head).contains(path.last)) { Option(path)}
	else {
		first(ordered_moves(dim,path,path.head), i =>first_closed_tour_heuristic(dim, i::path))
	}
}


//(3c) Same as (3b) but searches for *non-closed* tours. However, 
//     you have to be careful to write a tail-recursive version as this 
//     function will be called with dimensions of up to 40 * 40.

def first_tour_heuristic(dim: Int, path: Path) : Option[Path] = {
	
	def firstTour(xs: List[Pos]) : Option[Path] = {
	if (xs.isEmpty) None
	else{
		val x = first_tour_heuristic(dim, xs.head::path)
		if (x != None) x
		else{
			firstTour(xs.tail)
			}
		}
	}	

	if (path.length == dim*dim) Some(path)
	else {
		firstTour(ordered_moves(dim,path,path.head));
	}	
}

}
