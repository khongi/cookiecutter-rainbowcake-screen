# cookiecutter-rainbowcake-screen
A cookiecutter template for generating a [RainbowCake](https://rainbowcake.dev/) Fragment boilerplate.

## Prerequisites

You will need:
- Python
- cookiecutter (at least 2.0.0 on Windows)
- mako

Install cookiecutter and mako via pip:
- `pip install --user cookiecutter`
- `pip install --user mako`

Make sure your user site Scripts directory is added to your PATH.
You can find out where are your user-site packages with:

`python -m site --user-site`

For additional information visit the [cookiecutter docs](https://cookiecutter.readthedocs.io/en/stable/installation.html).

### Windows
There is a line endings [issue](https://github.com/cookiecutter/cookiecutter/issues/405) on Windows if you use the cookiecutter version below 2.0.0.
As of writing this, the [2.0.0 version](https://github.com/cookiecutter/cookiecutter/issues/1555) is not released yet but you can install it from git with few lines.

1. Uninstall your current version if your already have it installed
```Bash
pip uninstall cookiecutter
```
2. Clone cookiecutter
```Bash
git clone https://github.com/cookiecutter/cookiecutter.git
```
3. Setup cookiecutter
```Bash
python -m virtualenv cookiecutter
cd cookiecutter/
python setup.py develop
```

## Usage

Execute the following command in your *ui* package directory:

(e.g. ~/sources/myproject/app/src/main/java/com/example/**ui**)

`cookiecutter https://github.com/khongi/cookiecutter-rainbowcake-screen.git`

You will be promped to enter a few information about the screen.

```
screen_name [HelloWorld]:
```

* If you want a fragment with the name MyExampleFragment, you should enter **MyExample**.
* Make sure to use **P**ascal**C**ase as the generated content depends on it.

```
directory_name [helloworld]:
```

* This is the folder under which the Fragment and associated files will be generated.

```
Select di:
1 - Hilt
2 - Dagger 2
Choose from 1, 2 [1]:
```

* Choosing Hilt will add @AndroidEntryPoint and @HiltViewModel annotations

```
Select view_reference:
1 - ViewBinding
2 - Synthetics
Choose from 1, 2 [1]:
```

* Choosing ViewBinding will inflate the binding in `onCreateView`
* Choosing Synthetics will result in overriding `getViewResource` with the layout file

### Customizing

You can make the command shorter by creating a `.cookiecutterrc` file.

Make `.cookiecutterrc` file in your home directory with the content:
```yaml
abbreviations:
    ghrc: https://github.com/khongi/cookiecutter-rainbowcake-screen.git
    rc: cookiecutter-rainbowcake-screen
```

Now you can use the template like:

- `cookiecutter ghrc` : This will download the template from github.
- `cookiecutter rc` : If you already downloaded the template.

