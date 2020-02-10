require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-config-bridge"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  react-native-config-bridge
                   DESC
  s.homepage     = "https://github.com/digbang/react-native-config-bridge"
  s.license      = "MIT"
  s.authors      = { "Eze Livinsky" => "elivinsky@digbang.com" }
  s.platforms    = { :ios => "9.0" }
  s.source       = { :git => "https://github.com/digbang/react-native-config-bridge.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,swift}"
  s.requires_arc = true

  s.dependency "React"
end

