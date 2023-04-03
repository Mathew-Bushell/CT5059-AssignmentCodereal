import scala.io.Source
import scala.collection.mutable.ListBuffer
object Main {
  def main(args:Array[String]):Unit = {
    val connectives: List[String] = List("a", "an", "the", "for", "and", "nor", "but", "or", "yet", "so", "to", "at", "by", "from", "in", "into", "of", "on", "onto", "with", "as", "than", "up", "down", "off", "out", "over", "under", "above", "about", "after", "around", "before", "through", "throughout", "therefore", "nonetheless", "whereas", "finally", "furthermore", "until", "upon", "within", "am", "thus", "however")
    val flagWords: List[String] = List("threat", "vulnerabilities", "attack", "loopholes", "loophole", "whitelist", "blacklist", "hash", "encryption")
    val emailFile = Source.fromFile("Email.txt")
    val filteredEmail = new ListBuffer[String]()
    val words = new ListBuffer[String]()


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


    var susScore = 0
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