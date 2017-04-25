package functions

import scala.annotation.tailrec

object Glob {

  /**
    * See [[https://research.swtch.com/glob]]
    */
  def matches(pattern: String, name: String): Boolean = {
    @tailrec def go(px: Int = 0, nx: Int = 0, nextPx: Int = 0, nextNx: Int = 0): Boolean = {
      if (!(px < pattern.length || nx < name.length)) true // Matched all of pattern to all of name. Success.
      else if (px < pattern.length)
        pattern.charAt(px) match {
          case '*' => // zero-or-more-character wildcard
            // Try to match at nx.
            // If that doesn't work out,
            // restart at nx+1 next.
            go(px + 1, nx, px, nx + 1)
          case c => // ordinary character
            if (nx < name.length && name.charAt(nx) == c)
              go(px + 1, nx + 1, nextPx, nextNx)
            else if (0 < nextNx && nextNx <= name.length)
              go(nextPx, nextNx, nextPx, nextNx)
            else
              false
        }
      else if (0 < nextNx && nextNx <= name.length)
        go(nextPx, nextNx, nextPx, nextNx)
      else
        false
    }

    go()
  }
}
