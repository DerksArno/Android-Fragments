# Android Fragments
## Why use fragments
When rotating the device to landscape mode, the screen will have a lot of horizontal space unused. By changing the view in landscape mode to allow details to be seen in the same screen, the unused space will be filled and the user will have a better experience.

## How it works
The fragment classes will handle their own views, allowing the views to be re-used in different layouts.

## What you need
#### Classes
- MainActivity
  - Handles the layouts to be used based on orientation.
  - Handles the fragment transactions when an item gets selected.
- OverviewFragment
  - Inflates the view.
- DetailFragment
  - Inflates the view.
#### Layouts
- Activity_main_portrait
  - The layout to handle the portrait mode.
- Activity_main
  - The layout to handle the landscape mode.
- Overview_fragment
  - The list layout.
- Detail_fragment
  - The detail layout.
