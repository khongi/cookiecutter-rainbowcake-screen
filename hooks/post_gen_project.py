import os
from pathlib import Path

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
new_layout_file_name = 'fragment_' + screen_name_splitted + '.xml'
new_layout_file = os.path.join(layout_dir, new_layout_file_name)
os.rename(generated_layout_file, new_layout_file)