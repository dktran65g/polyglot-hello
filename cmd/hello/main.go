package main

import (
	"fmt"

	"github.com/polyglot-hello/packages/go-greeter"
	_ "github.com/swaggo/http-swagger"
)

func main() {
	fmt.Println("Hello, World! (from Go)")
	fmt.Println(greeter.Greet("Polyglot"))
}
