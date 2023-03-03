(ns chatgpt-cli.core
  (:require 
    [clj-http.client    :as client]
    [clojure.data.json  :as json])
  (:gen-class))

(def api-key (System/getenv "OPENAI_API_KEY"))

(defonce messages (atom [{:role    "system"
                          :content "You are a helpful assistant"}]))

(defn request [prompt]
  (let [messages (swap! messages conj {:role    "user"
                                       :content prompt})])
  (client/post "https://api.openai.com/v1/chat/completions"
               {:headers {"Content-Type" "application/json"
                          "Authorization" (str "Bearer " api-key)}
                :body    (json/write-str
                            {:model    "gpt-3.5-turbo"
                             :messages @messages})}))


(defn response [resp]
  (let [resp    (json/read-str (:body resp) :key-fn keyword)
        content (get-in resp [:choices 0 :message :content])]
    (swap! messages conj {:role    "assistant"
                          :content content})))

(defn -main []
  (println "Welcome to ChatGPT CLI")
  (print "Please enter a prompt: ")
  (flush)
  (loop [prompt (read-line)]
    (let [resp (response (request prompt))
          resp (:content (last @messages))]
      (println)
      (println "ChatGPT response:" resp)
      (println)
      (print "User: ")
      (flush)
      (recur (read-line)))))
    
