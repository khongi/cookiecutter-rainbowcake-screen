import re
import sys
import os
from pathlib import Path

PACKAGE_REGEX = r'^([A-Za-z]{1}[A-Za-z\d_]*\.)+[A-Za-z][A-Za-z\d_]*$'

package_name = '{{ cookiecutter.package_name }}'

if not re.match(PACKAGE_REGEX, package_name):
    print('ERROR: %s is not a valid package name!' % package_name)

    # exits with status 1 to indicate failure
    sys.exit(1)
