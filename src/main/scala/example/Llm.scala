package example

import com.donderom.llm4s.*

import java.nio.file.Paths

@main def example() = {
  val prompt = "Massacres of Poles in Volhynia and Eastern Galicia?"
  println(s"prompt=$prompt")

  System.load("/Users/do/git/llama.cpp/build/src/libllama.dylib")

  val model = Paths.get("/Users/do/git/codellama-7b.Q4_K_M.gguf")
  //  val model = Paths.get("/Users/do/git/codellama-7b.Q4_0.gguf")
  //  val model = Paths.get("/Users/do/git/llama-2-7b-chat.ggmlv3.q3_K_M.bin")
  //  val model = Paths.get("/Users/do/git/llama-2-7b-chat.ggmlv3.q4_K_M.bin")

  def llm = Llm(model, ModelParams(gpuLayers = 30))

  val params = LlmParams(ContextParams(size = 4096))

  // Completion
  {
    val l = llm
    // To print generation as it goes
    l(prompt, params).foreach { stream =>
      stream.foreach { token =>
        print(token)
      }
    }
    // Or build a string
    l(prompt, params).foreach { stream => println(stream.mkString) }
    l.close()
  }

  // Embeddings
  {
    val l = llm
    l.embeddings(prompt).foreach { embeddings =>
      embeddings.foreach { embd =>
        print(embd)
        print(' ')
      }
    }
    l.close()
  }
}
