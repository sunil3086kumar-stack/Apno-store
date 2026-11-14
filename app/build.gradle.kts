      - name: Build
        env:
          KEYSTORE_FILE: apno-release.jks
          KEYSTORE_PASSWORD: ${{ github.event.inputs.pass }}
          KEY_ALIAS: apno
          KEY_PASSWORD: ${{ github.event.inputs.pass }}
        run: |
          chmod +x gradlew
          ./gradlew assembleRelease bundleRelease --no-daemon
