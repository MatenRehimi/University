// Part 2 about Alcohol-Consumption Worldwide
//============================================

object CW6b {

import io.Source
import scala.util._

val url_alcohol = 
  "https://raw.githubusercontent.com/fivethirtyeight/data/master/alcohol-consumption/drinks.csv"

val file_population = 
  "population.csv"


//(1) Complete the get_csv_page function below. It takes a URL-string
//    as argument and generates a list of strings corresponding to each
//    line in the downloaded csv-list. The URL url_alcohol above is one 
//    possible argument.

def get_csv_page(url: String) : List[String] = {
	Source.fromURL(url).mkString.split("\n").toList

}


//    Complete the get_csv_file function below. It takes a file name 
//    as argument and reads the content of the given file. Like above,
//    it should generate a list of strings corresponding to each
//    line in the csv-list. The filename file_population is one possible
//    argument.

def get_csv_file(file: String) : List[String] = {
	Source.fromFile(file).mkString.split("\n").toList
}



//(2) Complete the functions that process the csv-lists. For
//    process_alcs extract the country name (as String) and the 
//    pure alcohol consumption (as Double). For process_pops
//    generate a Map of Strings (country names) to Long numbers 
//    (population sizes). 

def process_alcs(lines: List[String]) : List[(String, Double)] = {
	val list = for (i <- lines) yield i.split(",").toList
	val countryNames = for (i <- list) yield i(0)
	val alcoholConsumption = for (i <- list) yield i(4).toDouble
	
	(countryNames zip alcoholConsumption).toList
}

def process_pops(lines: List[String]) : Map[String, Long] = {
	val list = for (i <- lines) yield i.split(",").toList
	val countryNames = for (i <- list) yield i(0)
	val populationSize = for (i <- list) yield i(1).toLong
	(countryNames zip populationSize).toMap
}
//(3) Calculate for each country the overall alcohol_consumption using
//    the data from the alcohol list and the population sizes list. You
//    should only include countries on the alcohol list that are also
//    on the population sizes list with the exact same name. Note that
//    the spelling of some names in the alcohol list differs from the
//    population sizes list. You can ignore entries where the names differ. 
//    Sort the resulting list according to the country with the highest alcohol 
//    consumption to the country with the lowest alcohol consumption.

def sorted_country_consumption() /*: List[(String, Long)] */= {
	val alcohol_consumption = (process_alcs(get_csv_page(url_alcohol).drop(1))).toList
	val population_size = process_pops(get_csv_file(file_population).drop(1))

	val uniqueCountryNamesForPopulation = population_size.map(_._1).toSet
	val uniqueCountryNamesForAlcConsumption = alcohol_consumption.map(_._1).toSet

	val reducedAlcConsumption = (alcohol_consumption.toMap filterKeys uniqueCountryNamesForPopulation).toList.sortBy(_._1)
	val reducedPopulationSize = (population_size filterKeys uniqueCountryNamesForAlcConsumption).toList.sortBy(_._1)

	val lista = reducedPopulationSize.map(_._2).toList
	val listb = reducedAlcConsumption.map(_._2).toList

	val c = for (i<-0 to lista.size-1) yield (listb(i)*lista(i)).toLong
	(reducedAlcConsumption.map(_._1),c).zipped.toList.sortBy(_._2).reverse

}

//   Calculate the world consumption of pure alcohol of all countries, which 
//   should be the first element in the tuple below. The second element is
//   the overall consumption of the first n countries in the sorted list
//   from above; and finally the double should be the percentage of the 
//   first n countries drinking from the the world consumption of alcohol.          

def percentage(n: Int) /*: (Long, Long, Double)*/ =  {
	val sortedCountryConsumption = sorted_country_consumption();
	val totalAlcoholConsumption = sortedCountryConsumption.map(_._2).sum
	val temp = sortedCountryConsumption.map(_._2)
	val partialAlcoholConsumption = (for (i<-0 to n-1) yield temp(i)).sum
	val percentage = (partialAlcoholConsumption.toDouble/totalAlcoholConsumption.toDouble)*100
	(totalAlcoholConsumption,partialAlcoholConsumption, percentage)
}

}
