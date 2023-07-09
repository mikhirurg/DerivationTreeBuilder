# DerivationTreeBuilder

[![Build](https://github.com/mikhirurg/DerivationTreeBuilder/actions/workflows/maven.yml/badge.svg)](https://github.com/mikhirurg/DerivationTreeBuilder/actions/workflows/maven.yml)

## Project description

DerivationTreeBuilder is a tool that allows you to automatically generate natural semantics derivation trees for the While programming language programs.

## Installation guide

The project requires Java version 13+ in order to run the simulation. 
There are a few different ways how a user can obtain the jar file to execute the project:
- The latest stable version of the project is available in the [Releases](https://github.com/mikhirurg/DerivationTreeBuilder/releases) section of the repository
- The user can build the executable jar from sources by using the script ```build.sh```

After obtaining the executable jar, the user can just run the following command:

```
java -jar DerivationTreeBuilder.jar
```

## How to use the project

<img src="/img/img1.png" alt="Demo 1" width=800>

On the top part of the application window, there is a text area for the input program. 
Below that there is located the tree generation settings: 
- Do we want to use explicit state representation
- What is the derivation depth of the tree
- What tree representation format do we want (ASCII / LateX code)

Also, there is a button called "Build", which initiates the derivation tree-building process.

And, at the bottom of the application window, there is a second big text area that displays the output with the derivation tree.

## Examples

<table>
<tr>
<td> While program </td> <td> ASCII tree </td> <td> LateX tree </td>
</tr>
<tr>
<td>
  
```c
x:=27;
(
    y:=8;
    z:=0
)
```

</td>

<td>
  
[ASCII](/demo/demo1/ascii_tree.txt)
</td>

<td>
  
- [LateX](/demo/demo1/latex_tree.tex)

- [Image](/demo/demo1/img.png)
</td>

</tr>
<tr>
<td>
  
```c
x:=5;
y:=1;
i:=1;
while i <= x do
(
    y := y * x;
    x := x - 1
)
```

</td>

<td>
  
[ASCII](/demo/demo2/ascii_tree.txt)
</td>

<td>

- [LateX](/demo/demo2/latex_tree.tex)

- [Image](/demo/demo2/img.png)
</td>

</tr>
<tr>
<td>
  
```c
x:=27;
y:=8;
z:=0;
while y <= x do
(
    z := z + 1;
    x := x - y
)
```

</td>

<td>

[ASCII](/demo/demo3/ascii_tree.txt)
</td>

<td>

- [LateX](/demo/demo3/latex_tree.tex)

- [Image](/demo/demo3/img.png)
</td>

</tr>
<tr>
<td>
  
```c
(
    i:=0;
    while i <= 3 do
    (
        i := i + 1
    )
);
while 0 <= i do
(
    i := i - 1
)
```

</td>

<td>
  
[ASCII](/demo/demo4/ascii_tree.txt)
</td>

<td>

- [LateX](/demo/demo4/latex_tree.tex)

- [Image](/demo/demo4/img.png)
</td>

</tr>
</table>

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

## License

[MIT](/LICENSE.txt)
