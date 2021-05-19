import os
import sys
from pathlib import Path
from mako.template import Template
from mako.lookup import TemplateLookup

def split_on_uppercase(s, keep_contiguous=False):
    """

    Args:
        s (str): string
        keep_contiguous (bool): flag to indicate we want to 
                                keep contiguous uppercase chars together

    Returns:

    """

    string_length = len(s)
    is_lower_around = (lambda: s[i-1].islower() or 
                       string_length > (i + 1) and s[i + 1].islower())

    start = 0
    parts = []
    for i in range(1, string_length):
        if s[i].isupper() and (not keep_contiguous or is_lower_around()):
            parts.append(s[start: i])
            start = i
    parts.append(s[start:])

    return parts

def find_node(start_path, searching_for, is_file=False):
    last_root = start_path
    current_root = start_path

    while current_root:
        pruned = False
        for root, dirs, files in os.walk(current_root):
            if not pruned:
                try:
                    # Remove the part of the tree we already searched
                    del dirs[dirs.index(os.path.basename(last_root))]
                    pruned = True
                except ValueError:
                    pass
            if (is_file):
                if searching_for in files:
                    # Found the searched file
                    return os.path.join(root, searching_for)
            else:
                if searching_for in dirs:
                    # Found the searched dir
                    return os.path.join(root, searching_for)
                
        # Otherwise, pop up a level, search again
        last_root = current_root
        current_root = os.path.dirname(last_root)

def get_package_name(path):
    src_sub_section = os.sep + 'src' + os.sep
    section_after_src = current_dir.split(src_sub_section, 1)[1]

    java_sub_section = os.sep + 'java' + os.sep
    kotlin_sub_section = os.sep + 'kotlin' + os.sep

    section_with_package = ''
    if java_sub_section in section_after_src:
        section_with_package = section_after_src.split(java_sub_section, 1)[1]
    elif kotlin_sub_section in section_after_src:
        section_with_package = section_after_src.split(kotlin_sub_section, 1)[1]
    else:
        print('ERROR: not a valid java or kotlin folder structure')
        sys.exit(1)

    return section_with_package.replace(os.sep,'.')

def save_file(filepath, content):
    f=open(filepath,'w')
    f.write(content)
    f.close()

current_dir = os.getcwd()

# Find resources directory
res_dir = find_node(os.getcwd(), 'res')

# Get generated layout file
generated_layout_name = "$LAYOUTFILE.xml"
generated_layout_file = os.path.join(current_dir, generated_layout_name)

# Make sure layout directory exists
layout_dir = os.path.join(res_dir, "layout/")
Path(layout_dir).mkdir(parents=True, exist_ok=True)

# Move generated layout file to layout directory with proper name
screen_name_splitted = '_'.join(split_on_uppercase("{{ cookiecutter.screen_name }}")).lower()
new_layout_name = 'fragment_' + screen_name_splitted
new_layout_file_name = new_layout_name + '.xml'
new_layout_file = os.path.join(layout_dir, new_layout_file_name)
os.rename(generated_layout_file, new_layout_file)

# Get generated files package name from folder structure
package_name = get_package_name(current_dir)

# Get app package name from AndroidManifest.xml
manifest_filepath = find_node(current_dir, 'AndroidManifest.xml', True)
with open(manifest_filepath, 'r') as f:
    searchlines = f.readlines()
for i, line in enumerate(searchlines):
    if ('package=\"') in line:
        section_with_app_package = line.split('package=', 1)[1]
        app_package = section_with_app_package.split('\"', 2)[1]

templates = TemplateLookup(directories=[current_dir], strict_undefined=True)

for subdir, dirs, files in os.walk(current_dir):
    for filename in files:
        # render file content
        template = templates.get_template(filename)
        rendered = template.render(
            package_name=package_name, 
            app_package=app_package, 
            layout_name=new_layout_name
        )

        # save file with modified content
        filepath = os.path.join(subdir, filename)
        save_file(filepath, rendered)
