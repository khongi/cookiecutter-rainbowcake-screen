# cookiecutter-rainbowcake-screen
A cookiecutter template for generating a RainbowCake Fragment boilerplate.

## Prerequisites

You will need:
- Python
- cookiecutter
- make

Install cookiecutter and mako via pip:
- `pip install --user cookiecutter`
- `pip install --user mako`

For additional information visit [cookiecutter docs](https://cookiecutter.readthedocs.io/en/stable/installation.html).

## Usage

Execute the following command in your *ui* package directory:

(e.g. ~/sources/myproject/app/src/main/java/com/example/**ui**)

`cookiecutter https://github.com/khongi/cookiecutter-rainbowcake-screen.git`

You can make the command shorter with `.cookiecutterrc` file.

Create a `.cookiecutterrc` file in your home directory with the content:
```yaml
abbreviations:
    ghrc: https://github.com/khongi/cookiecutter-rainbowcake-screen.git
    rc: cookiecutter-rainbowcake-screen
```

Now you can use the template like:

- `cookiecutter ghrc` : This will download the template from github.
- `cookiecutter rc` : If you already downloaded the template.

