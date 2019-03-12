// Copyright 2019 Flying Vehicle Monster team
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import Foundation

internal class Log {
    internal static var shouldLog = true
    fileprivate static var dateFormatter: DateFormatter {
        let formatter = DateFormatter()
        formatter.dateFormat = dateFormat
        formatter.locale = Locale.current
        formatter.timeZone = TimeZone.current
        return formatter
    }
    private static var dateFormat = "yyyy-MM-dd hh:mm:ss"
    
    public class func info(_ object: Any, fileName: String = #file, line: Int = #line, col: Int = #column, methodName: String = #function) {
        if shouldLog {
            log("[\(Date().toString())] [\(LogEvent.info.rawValue)] [\(sourceFileName(fileName))]:(\(line), \(col)) at: \(methodName) -> \(object)")
        }
    }
    
    public class func debug(_ object: Any, fileName: String = #file, line: Int = #line, col: Int = #column, methodName: String = #function) {
        if shouldLog {
            log("[\(Date().toString())] [\(LogEvent.debug.rawValue)] [\(sourceFileName(fileName))]:(\(line), \(col)) at: \(methodName) -> \(object)")
        }
    }
    
    public class func warning(_ object: Any, fileName: String = #file, line: Int = #line, col: Int = #column, methodName: String = #function) {
        if shouldLog {
            log("[\(Date().toString())] [\(LogEvent.warning.rawValue)] [\(sourceFileName(fileName))]:(\(line), \(col)) at: \(methodName) -> \(object)")
        }
    }
    
    public class func error(_ object: Any, fileName: String = #file, line: Int = #line, col: Int = #column, methodName: String = #function) {
        if shouldLog {
            log("[\(Date().toString())] [\(LogEvent.error.rawValue)] [\(sourceFileName(fileName))]:(\(line), \(col)) at: \(methodName) -> \(object)")
        }
    }
    
    private class func sourceFileName(_ filePath: String) -> String {
        let components = filePath.components(separatedBy: "/")
        return components.isEmpty ? "" : components.last!
    }
    
    private class func log(_ object: Any) {
        #if DEBUG
            Swift.print(object)
        #endif
    }
}

extension Date {
    func toString() -> String {
        return Log.dateFormatter.string(from: self as Date)
    }
}
