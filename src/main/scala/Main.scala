import scala.io.Source
//import scala.collection.mutable.ListBuffer
import scala.collection.mutable
//import scala.collection.mutable.Map
import scala.collection.mutable.ListMap
object Main {
  def main(args:Array[String]):Unit = {
    val connectives: List[String] = List("a", "an", "the", "for", "and", "nor", "but", "or", "yet", "so", "to", "at", "by", "from", "in", "into", "of", "on", "onto", "with", "as", "than", "up", "down", "off", "out", "over", "under", "above", "about", "after", "around", "before", "through", "throughout", "therefore", "nonetheless", "whereas", "finally", "furthermore", "until", "upon", "within", "am", "thus", "however")
    val flagWords: List[String] = List("threat", "vulnerabilities", "attack", "loopholes", "loophole", "whitelist", "blacklist", "hash", "encryption")
    val emailFile = Source.fromFile("Email.txt")
    val filteredEmail = new mutable.ListBuffer[String]()
    val words = new mutable.ListBuffer[String]()
    var wordFrequency = scala.collection.mutable.Map[String, Int]()

    for (line <- emailFile.getLines) {
      var standardLine = line.toLowerCase()
      standardLine = standardLine.replace(".","")
      standardLine = standardLine.replace(",","")
      standardLine = standardLine.replace("\n","")
      val splitLine = standardLine.split(" ")
      for (word <- splitLine) {
        var wordConn = false
        for (conn <- connectives) {
          if (conn == word) {
            wordConn = true
          }

        }
        if (wordConn == false) {
          filteredEmail += word
        }
      }
    }
    val filteredEmailList = filteredEmail.toList
//    println(filteredEmailList)
    emailFile.close()

    for (word <- filteredEmailList){
      var newWord = true

      for (mapWord <- words) {

        if (word == mapWord) {
//          println(word)
          newWord = false

        }
      }
      if(newWord == true){
        words += word

      }
    }
    val wordss = words.toList
    println(wordss)
    for (word <- wordss) {
      wordFrequency += (word -> filteredEmailList.count(_ == word))
    }
    var highest = 1
    for ((key,value) <- wordFrequency){


      if (value > highest){
        highest = value
//        println("highest= "+highest)
      }else{
//        println("highest remains same")
      }
//    set up a new mutable list to add the selected 10 words and a boolean to loop
      val topTen = new mutable.ListBuffer[String]()
      var loopN = 0
      var noVal = true
      var ignore = false
      while (loopN < 10){
        for ((key,value) <- wordFrequency){

        }
        loopN = loopN + 1
        if (noVal == true){
          highest = highest - 1
          println(loopN)
        }
      Thread.sleep(1000)
      }

    }
//    val wordFrequency2 = wordFrequency.toSeq
//    val wordFrequency3 = ListMap(wordFrequency2.toSeq.sortWith(_._2>_._2):_*)
//    println(wordFrequency2.keys)
//    println(wordFrequency2.values)
//    println(wordFrequency2)
    var susScore = 0
    //take an int value for each map item and "blacklist" selected ones that are already in the top 5
    for(x <- filteredEmailList){
      for (flag <- flagWords){
        if(x == flag){
          susScore += 1

        }
      }
    }
    println (susScore)
    if(susScore >= 5){
      println("This email has been classified as suspicious")
    }else{
      println("This email is not suspicious")
    }
  }
}
//either utilise and sort list by .count() or utilise a map which counts repeats and puts values in map
//https://www.baeldung.com/scala/list-count-occurrences