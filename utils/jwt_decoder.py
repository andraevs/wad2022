import base64
import json

def decode_jwt(jwt):
    try:
        # Split the JWT into its three parts: header, payload, and signature
        header, payload, signature = jwt.split('.')

        # Decode the base64url-encoded parts
        decoded_header = base64.urlsafe_b64decode(header + '===').decode('utf-8')
        decoded_payload = base64.urlsafe_b64decode(payload + '===').decode('utf-8')

        # Parse the JSON data
        header_data = json.loads(decoded_header)
        payload_data = json.loads(decoded_payload)

        return header_data, payload_data

    except ValueError:
        return None, None

def print_jwt_details(jwt):
    header, payload = decode_jwt(jwt)

    if header is None or payload is None:
        print("Invalid JWT format")
        return

    print("Header:")
    print(json.dumps(header, indent=4))
    print("\nPayload:")
    print(json.dumps(payload, indent=4))

if __name__ == "__main__":
    jwt = input("Enter JWT: ")
    print_jwt_details(jwt)
