import scala.io.Source

object Main {
  def main(args:String) = {
    val connectives: List[String] = List("a", "an", "the", "for", "and", "nor", "but", "or", "yet", "so", "to", "at", "by", "from", "in", "into", "of", "on", "onto", "with", "as", "than", "up", "down", "off", "out", "over", "under", "above", "about", "after", "around", "before", "through", "throughout", "therefore", "nonetheless", "whereas", "finally", "furthermore", "until", "upon", "within")
    val flagWords: List[String] = List("Threat", "Vulnerabilities", "Attack", "loopholes", "whitelist", "Blacklist", "hash", "encryption")
    val emailFile = Source.fromFile("Email.txt")
    for (line <- emailFile.getLines) {
      println(line.toLowerCase())

    }
    emailFile.close()
  }
}
